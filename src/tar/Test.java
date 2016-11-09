package tar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;

import bean.TarProcessInfo;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		///D:\test\fffe036f-c98c-4df2-96e1-7327b6778db9
		 String filesrcdir ="D:/test" ;
		 String fileoutdir ="D:/yjq" ;
		 String dir="ffe" ;
		 String fix = "fix" ;
//		 TarProcessInfo tarProcess = new TarProcessInfo(filesrcdir, fileoutdir, dir,fix, 2) ;
//		 TarRunnable  tarunable = new TarRunnable(tarProcess) ;
//		 Thread   tarthread = new Thread(tarunable) ;
//		 tarthread.start();
		

		 
		
// 		启动生成文件线程		
//		for(int i = 0 ; i <60 ; i++){
//			new NewfilesTherad(i+"_y").start();
//		}
//		
		
	}
	public static void testparttenmatch(){
		
		long time1=System.currentTimeMillis(); 
		File f = new File("d:/test");
		String[] files = f.list(new FilenameFilter() {
		    @Override
		    public boolean accept(File dir, String name) {
		        return name.startsWith("ffe");
		    }
		});
		System.out.println(files.length);
		for(int i = 0 ; i < files.length ; i++){
			System.out.println(i+"-"+files[i]);
		}
		long time2=System.currentTimeMillis();  
	    long interval=time2-time1;  
	    System.out.println(interval);
		System.out.println("end");
	}
	
	
	
	
	

//测试在文件数目比较多的情况下 能否找到对应的文件夹 并列出文件夹下的文件
	public static void testfinfiledir(){
		String basepath = "d:/test/" ;
		String fdirna = "ffef22ea-c721-4144-b52b-e3ba332a5e16" ;
		
	    long time1=System.currentTimeMillis();  
	    File file =new File(basepath+fdirna);  
	    if(file .exists()  && file .isDirectory())      
		{       
	    	 File[] files = file.listFiles();
	    	 for (File f : files) {
	    		 System.out.println(f.getName());  
	 		}
		}
	   
	    long time2=System.currentTimeMillis();  
	    long interval=time2-time1;  
	    System.out.println(interval);
//	    03fba6dc-5597-4cf1-8da3-dfd6cdb76e39
//	    3c3171a2-6dda-4b75-a771-063ac9129d50
//	    4f322de4-549a-4fa7-bc76-40ac0f55b0ab
//	    74d6dd49-0f5e-4bb0-b1cc-401d4f9e37b5
//	    8a3cf536-49b0-43cd-8306-9c0e2bd7f6aa
//	    a2849625-1a1e-471e-8ed1-00d0617740ae
//	    a6631e61-1c27-4058-a8f7-522deb6cdfef
//	    d2bdfaf3-d396-44d7-b6de-eb341457542f
//	    ec60a570-2758-4de8-a1b8-42cb427f6e2b
//	    fd43d602-ceeb-462e-966d-39210f735bba
//	    24 ms  可以找到  时间较短
	}
	

}







class NewfilesTherad  extends Thread {
	public String name ="" ;
	
	NewfilesTherad (String name){
        super(name);//调用父类带参数的构造方法
        this.name = name ;
    }
	
	public void run(){
		
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		sb.append("\r\n");
		sb.append("<dataGroup>");
		sb.append("\r\n\t");
		sb.append("<dataItem>");
		sb.append("\r\n\t\t");
		sb.append("<data>");
		sb.append("Test");
		sb.append("</data>");
		sb.append("\r\n\t");
		sb.append("<dataItem>");
		sb.append("\r\n");
		sb.append("</dataGroup>");
		String inputStr = sb.toString();
		String path = "d:/test" ;
		byte[] contentOfEntry = inputStr.getBytes();
		
		File file  = null ;
		String fdir = "" ;
		while(true){
			 fdir =  path+"/"+UUID.randomUUID() ;
			 file =new File(fdir);    
			if(!file .exists()  && !file .isDirectory())      
			{       
			    file .mkdir();    
			}
			String fdirna = "" ;
			FileOutputStream fos = null ;
			for(int i = 0 ; i <10 ; i++){
				fdirna = fdir + "/"+UUID.randomUUID() ;
				try {
					fos = new FileOutputStream(fdirna);
					fos.write(contentOfEntry);
					fos.flush();
					fos.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			System.out.println(name);
		}
	}	
	
}