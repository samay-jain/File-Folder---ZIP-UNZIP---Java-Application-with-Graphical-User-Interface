import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
public class TOOLCODE {
	public boolean zipFile(String inputPath,String outputPath,String fname)
	{
		try {
			File file = new File(inputPath);
            FileOutputStream fos = new FileOutputStream(outputPath);
            ZipOutputStream zos = new ZipOutputStream(fos);
            ZipEntry ze = new ZipEntry(file.getName());
            zos.putNextEntry(ze);
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }
            zos.closeEntry();
            zos.close();
            fis.close();
            fos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
		/*byte buffer[] = new byte[1024];
		try {
			FileInputStream inp = new FileInputStream(inputPath);
			FileOutputStream out = new FileOutputStream(outputPath);
			ZipOutputStream zipstream = new ZipOutputStream(out);
			ZipEntry ze = new ZipEntry(fname);
			zipstream.putNextEntry(ze);
			int len=0;
			while((len=inp.read(buffer))!=-1)
			{
				zipstream.write(buffer,0,len);
			}
			inp.close();
			zipstream.closeEntry();
			out.close();
			zipstream.close();
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}*/
	}
	
	
	private static String OUTPUT_ZIP_FILE="C:\\\\Users\\\\Admin\\\\Desktop\\\\abc.zip";
    private static String SOURCE_FOLDER ="";
	List<String> fileList = new ArrayList<>();
	public boolean zipFolder(String inpzip,String optzip)
	{
		SOURCE_FOLDER=inpzip;
		if(!optzip.isEmpty())
			OUTPUT_ZIP_FILE=optzip;
		boolean result = zipIt(OUTPUT_ZIP_FILE);
		if(result)
			return true;
		else
			return false;
	}

    public boolean zipIt(String zipFile)
    {
    	byte[] buffer = new byte[1024];
    	try{
	    	File source = new File(SOURCE_FOLDER); 
			generateFileList(source);
    		FileOutputStream fos = new FileOutputStream(zipFile);
    		ZipOutputStream zos = new ZipOutputStream(fos);
    		for(String file : fileList)
    		{
    			ZipEntry ze= new ZipEntry(file.substring(source.getAbsolutePath().length()+1, file.length()));
    			zos.putNextEntry(ze);
    			FileInputStream in = new FileInputStream(file);
    			int len=0;
    			while((len=in.read(buffer))!=-1)
    			{
    				zos.write(buffer, 0, len);
    			}
        		zos.closeEntry();
    			in.close();
    		}
    		zos.close();
    		fos.close();
    		return true;
    	}
    	catch(IOException ex)
    	{
    		ex.printStackTrace();
    		return false;
    	}
   }

    public void generateFileList(File node)
    {
    	File[] files = node.listFiles();
        for(File file : files){
            if(file.isFile()) 
            	fileList.add(file.getAbsolutePath());
            else 
            	generateFileList(file);
        }
//    	if(node.isFile())
//    	{
//    		fileList.add(generateZipEntry(node.getAbsoluteFile().toString()));
//    	}
//    	if(node.isDirectory())
//    	{
//    		String[] subNote = node.list();
//    		for(String filename : subNote){
//    			generateFileList(new File(node, filename));
//    		}
//    	}
    }
    private String generateZipEntry(String file)
    {
    	return file.substring(SOURCE_FOLDER.length()+1, file.length());
    }
	
	
	
	
    private static String INPUT_ZIP_FILE = "";
    private static String OUTPUT_FOLDER = "C:\\\\Users\\\\Admin\\\\Desktop\\\\";
	public boolean unzipAny(String inp,String outp)
	{
		INPUT_ZIP_FILE = inp;
		if(!outp.isEmpty())
			OUTPUT_FOLDER = outp;
	    boolean result=unZipIt(INPUT_ZIP_FILE,OUTPUT_FOLDER);
    	if(result==true)
    		return true;
    	else
    		return false;
	}
	public boolean unZipIt(String zipFile, String outputFolder)
	{
	     byte[] buffer = new byte[1024];
	     try
	     {
	    	ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
	    	ZipEntry ze = zis.getNextEntry();
	    	 File folder = new File(OUTPUT_FOLDER);
	    	 if(!folder.exists())
	    	 {
	    		 folder.mkdir();
	    	 }
	    	while(ze!=null)
	    	{
	    		String fileName = outputFolder+File.separator+ze.getName();
	    		if(!ze.isDirectory())
	    		{
	    			FileOutputStream fos = new FileOutputStream(fileName);
	    			int len;
		            while ((len = zis.read(buffer))!=-1) 
		            {
		            	fos.write(buffer, 0, len);
		            }
		            fos.close();
	    		}
	    		else 
	    		{
	    			File dir = new File(fileName);
	    			dir.mkdir();
	    		}
	            zis.closeEntry();
	            ze = zis.getNextEntry();
	    	}
	        zis.closeEntry();
	    	zis.close();
	    	return true;
	     }
	     catch(IOException ex)
	     {
	    	 ex.printStackTrace();
	    	 return false;
	     }
	}
}
