package bean;

import java.util.Locale;
import java.util.concurrent.locks.ReentrantLock;

public class TarProcessInfo {
	//private ReentrantLock lock = new ReentrantLock();
	private String filesrcdir ;
	private String fileoutdir ;
	private String prifix ;
	private String dir ;
	private int kind ;
	private int count ;
	private int done ;
	
	public void adddone(){
		done++ ;
	}
	
	public int getDone() {
		return done;
	}
	
	
	public void setDone(int done) {
		this.done = done;
	}
	private boolean oncancel ;
	private boolean flag ;
	private String error ;
	
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public boolean isOncancel() {
		return oncancel;
	}
	public void setOncancel(boolean oncancel) {
		this.oncancel = oncancel;
	}
	public TarProcessInfo() {
		super();
		filesrcdir=""; 
		fileoutdir="";
		dir ="" ;
		kind = 0 ;		
		count = 0 ;
		done = 0 ;
		oncancel = false ;
		flag = true ;
	}
	
	


	public String getPrifix() {
		return prifix;
	}

	public void setPrifix(String prifix) {
		this.prifix = prifix;
	}

	public TarProcessInfo(String filesrcdir, String fileoutdir, String dir, String prifix, int kind) {
		super();
		this.filesrcdir = filesrcdir;
		this.fileoutdir = fileoutdir;
		this.dir = dir;
		this.kind = kind;
		this.count = 0 ;
		this.done = 0 ;
		this.oncancel = false ;
		this.flag = true ;
		this.prifix = prifix ;
	}
	public String getFilesrcdir() {
		return filesrcdir;
	}
	public void setFilesrcdir(String filesrcdir) {
		this.filesrcdir = filesrcdir;
	}
	public String getFileoutdir() {
		return fileoutdir;
	}
	public void setFileoutdir(String fileoutdir) {
		this.fileoutdir = fileoutdir;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public int getKind() {
		return kind;
	}
	public void setKind(int kind) {
		this.kind = kind;
	}

	
	
	
}
