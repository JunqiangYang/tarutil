package tar;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;

import bean.TarProcessInfo;

public class TarB implements Runnable {

	private static final String BASE_DIR = "";
	// 符号"/"用来作为目录标识判断符
	private static final String PATH = "/";
	private static final int BUFFER = 1024;
	private static final String EXT = ".tar";
	private TarProcessInfo tarprocesss;

	public TarB(TarProcessInfo tarpro) {
		tarprocesss = tarpro;
	}



	public File CreateOutFile(String outputFilename){
		File file = new File(outputFilename + EXT);
		if (!file.getParentFile().exists()) {
			if (!file.getParentFile().mkdirs()) {
			 return null ;
			}
		}
		try {
				if (!file.createNewFile()) {
					 return null ;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return file ;
	}
	
	
	/**
	 * 归档
	 * 
	 * @param srcFile
	 *            源路径
	 * @param taos
	 *            TarArchiveOutputStream
	 * @param basePath
	 *            归档包内相对路径
	 * @throws Exception
	 */
	private void archive(File srcFile, TarArchiveOutputStream taos, String basePath) throws Exception {
		if (srcFile.isDirectory()) {
			archiveDir(srcFile, taos, basePath);
		} else {
			archiveFile(srcFile, taos, basePath);
		}
	}

	/**
	 * 目录归档
	 * 
	 * @param dir
	 * @param taos
	 *            TarArchiveOutputStream
	 * @param basePath
	 * @throws Exception
	 */
	private void archiveDir(File dir, TarArchiveOutputStream taos, String basePath) throws Exception {

		File[] files = dir.listFiles();

		if (files.length < 1) {
			TarArchiveEntry entry = new TarArchiveEntry(basePath + dir.getName() + PATH);
			taos.putArchiveEntry(entry);
			taos.closeArchiveEntry();
		}

		for (File file : files) {
			// 递归归档
			archive(file, taos, basePath + dir.getName() + PATH);
		}

	}

	/**
	 * 数据归档
	 * 
	 * @param data
	 *            待归档数据
	 * @param path
	 *            归档数据的当前路径
	 * @param name
	 *            归档文件名
	 * @param taos
	 *            TarArchiveOutputStream
	 * @throws Exception
	 */
	private void archiveFile(File file, TarArchiveOutputStream taos, String dir) throws Exception {
		TarArchiveEntry entry = new TarArchiveEntry(dir + file.getName());
		entry.setSize(file.length());
		taos.putArchiveEntry(entry);
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		int count;
		byte data[] = new byte[BUFFER];
		while ((count = bis.read(data, 0, BUFFER)) != -1) {
			taos.write(data, 0, count);
		}
		bis.close();
		taos.closeArchiveEntry();
		taos.flush();
	}

	@Override
	public void run() {
		if (tarprocesss.getKind() == 2) { 
			File dir =  new File(tarprocesss.getFilesrcdir()) ;	
			String[] filenames  = dir.list(new FilenameFilter() {
				    @Override
				    public boolean accept(File dir, String name) {
				        return name.startsWith(tarprocesss.getDir());
				    }
				});
			tarprocesss.setCount(filenames.length);
			// 创建输出文件.
			String outputFilename = tarprocesss.getFileoutdir() + File.separator + tarprocesss.getDir()+ "_" + tarprocesss.getPrifix(); // 这里还没有添加后缀.+"后缀"
			File outFile = CreateOutFile(outputFilename) ;
			if(null == outFile){
				tarprocesss.setError("不能创建输出目录");
				return ;
			}	
			TarArchiveOutputStream taos;
			try {
				taos = new TarArchiveOutputStream(new FileOutputStream(outFile));
				taos.setLongFileMode(TarArchiveOutputStream.LONGFILE_GNU);
			} catch (FileNotFoundException e1) {
				tarprocesss.setError("不能输出压缩包");
				return ;
			}
			
			try {
					File file = null ;
					for (String filename : filenames) {
						if(!tarprocesss.isFlag()) { break ; }
						file =  new File(tarprocesss.getFilesrcdir()+PATH+filename) ;
						archive(file, taos,tarprocesss.getDir()+ PATH);
						//Thread.sleep(1000);
						tarprocesss.adddone();
					}
					taos.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				System.out.println(Thread.currentThread().getName() +"  -end ");
			}
		}
	}

}
