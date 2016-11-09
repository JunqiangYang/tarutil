package tar;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;

import bean.TarProcessInfo;

public class TarRunnable  {
	private TarProcessInfo tarprocesss;
	private Thread[] threads ;
	private int threadNum = 5 ;
	private ConcurrentLinkedQueue<String> fileinputqueue =  new ConcurrentLinkedQueue<String>();
	
	public TarProcessInfo getTarprocesss() {
		return tarprocesss;
	}

	public void setTarprocesss(TarProcessInfo tarprocesss) {
		this.tarprocesss = tarprocesss;
	}

	public ConcurrentLinkedQueue<String> getFileinputqueue() {
		return fileinputqueue;
	}

	public void setFileinputqueue(ConcurrentLinkedQueue<String> fileinputqueue) {
		this.fileinputqueue = fileinputqueue;
	}

	public TarRunnable(TarProcessInfo tarpro) {
		tarprocesss = tarpro;
		fileinputqueue =  new ConcurrentLinkedQueue<String>() ;
	}
	
	public void tar() {
		threadNum = 5 ;
		threads = new Thread[threadNum] ;
		// TODO Auto-generated method stub
		if (tarprocesss.getKind() == 1) { // 方式A 压缩一个指定的文件夹下的文件. 到指定的目录
			File dir =  new File(tarprocesss.getFilesrcdir() + File.separator + tarprocesss.getDir()) ;	
			int count  = dir.listFiles().length ;
			tarprocesss.setCount(count);
			String[] files = dir.list(); //创建输出文件列表.
			fileinputqueue.addAll(Arrays.asList(files));
			//System.out.println(fileinputqueue.size() +"--"+ (null ==fileinputqueue )+" "+fileinputqueue.poll());
			for(int i = 0 ; i < threadNum ; i++){
				TarA tara = new TarA(tarprocesss,fileinputqueue) ;
				tara.setFileinputqueue(fileinputqueue);
				threads[i] = new Thread(tara);
				threads[i].start();
			}
		} else {// 方式B 指定文件夹目录 压缩带有相似前缀的文件夹 到一个输出目录下.
			threads = new Thread[1] ;
			threads[0] = new Thread(new TarB(tarprocesss));
			threads[0].start();
		}
	}



	public void stoptar() {
		// TODO Auto-generated method stub
		// 停止压缩的线程 , 
		tarprocesss.setFlag(false);
		for(int i = 0 ; i < threadNum ; i++){
			threads[i].interrupt();
		}
	}

}
