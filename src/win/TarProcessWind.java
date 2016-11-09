package win;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRootPane;

import bean.TarProcessInfo;
import tar.TarRunnable;
import tar.TarUtils;

public class TarProcessWind extends JDialog {
	
	
	private TarRunnable tarunable ;
	private TarProcessWind tarprocesswind ;
	
	private JLabel hasdone  ; 
	private JLabel hasdonenum  ; 
	private JLabel all  ; 
	private JLabel allnum  ; 
	private JPanel center ;
	
	private JButton backbtn ;
	private JButton candonebtn ;
	
	private JProgressBar progress; // 进度条
	private JLabel words ;
	//private Long countFils = 0L ;
	//JButton inputFilechosebtn = new JButton("取消") ;
	private volatile  boolean  flag  =  true ;
	private List<String> dirnameList = null ;
	private String filesrcdir ="" ;
	private String fileoutdir ="" ;
	private String dir="" ;
	private int kind ;
	
	private TarProcessInfo tarProcess ;
	private Timer timer= new Timer();
	
    TimerTask  task = new TimerTask (){
		    public void run() {
		         doFlashProgress();
		       }
    };
	public TarProcessWind(String[] dirnameList , String filesrcdir , String fileoutdir ,String dir,String prifix ,int kind){
		dirnameList = dirnameList ;
		filesrcdir = filesrcdir ;
		fileoutdir = fileoutdir ;
		dir = dir ;
		kind = kind ;
		tarprocesswind = this ;
		tarProcess = new TarProcessInfo(filesrcdir, fileoutdir, dir,prifix, kind) ;
        this.setTitle("打包进度");
        this.setModal(true);
        this.setUndecorated(true);
        this.setResizable(true);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setSize(350, 100);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				if(tarProcess.getDone()>0 && tarProcess.getDone()!=tarProcess.getCount() ){
			 		   int exi = JOptionPane.showConfirmDialog(null, "确定要停止打包线程吗？", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			            if (exi == JOptionPane.YES_OPTION)
			            {
			                System.out.println("stop");
			                tarProcess.setFlag(false);
			                tarunable.stoptar();
			     		    timer.cancel();
			     		    tarprocesswind.dispose();
			            }
			            else
			            {
			            	System.out.println("go on ");
			                return;
			            }
				}
				if(tarProcess.getDone()==tarProcess.getCount() ){
		     		    timer.cancel();
		     		    tarprocesswind.dispose();
				}
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        tarunable = new TarRunnable(tarProcess) ;
        tarunable.tar();
        timer.schedule(task, 1000, 2000);
        words = new JLabel("请稍等...") ;
    	hasdone   = new JLabel("已打包的文件数:") ; 
    	hasdonenum   = new JLabel("0") ; 
    	all   = new JLabel("总扫描到文件数:") ; 
    	allnum   = new JLabel("0") ; 
    	JPanel soup = new JPanel() ; 
    	soup.add(hasdone);
    	soup.add(hasdonenum);
    	soup.add(new JLabel("    "));
    	soup.add(all);
    	soup.add(allnum);
    	center = new JPanel();
    	center.setLayout(new BorderLayout());
    	center.add(soup,BorderLayout.SOUTH) ;
        this.add(words,BorderLayout.NORTH) ;
        this.add(center,BorderLayout.CENTER) ;
        backbtn = new JButton("返回") ;
        candonebtn = new JButton("取消");
        backbtn.addActionListener(new  ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(tarProcess.getDone()==tarProcess.getCount() ){
	     		    timer.cancel();
	     		    tarprocesswind.dispose();
				}
			}
		});
        candonebtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(tarProcess.getDone()>0 && tarProcess.getDone()!=tarProcess.getCount() ){
			 		   int exi = JOptionPane.showConfirmDialog(null, "确定要停止打包线程吗？", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			            if (exi == JOptionPane.YES_OPTION)
			            {
			                System.out.println("stop");
			                tarProcess.setFlag(false);
			                tarunable.stoptar();
			     		    timer.cancel();
			     		    tarprocesswind.dispose();
			            }
			            else
			            {
			                return;
			            }
				}else{
					timer.cancel();
	     		    tarprocesswind.dispose();
				}
			}
		});
        JPanel southp = new JPanel() ;
        southp.add(backbtn) ;
        southp.add(candonebtn) ;
        this.add(southp,BorderLayout.SOUTH) ;
        this.setVisible(true);
	}
	
	public void newProgress(int max){
		    progress = new JProgressBar(1, max); // 实例化进度条
	        progress.setStringPainted(true);      // 描绘文字
	        progress.setBackground(Color.WHITE); // 设置背景色
	        progress.setForeground(Color.BLUE);
	        progress.setVisible(true);
	        center.add(progress,BorderLayout.CENTER);
	        this.resize(351, 101);
	}
	
		
	public void doFlashProgress(){
		if(null != tarProcess.getError()){
			JOptionPane.showMessageDialog(this,tarProcess.getError());
			tarProcess.setFlag(false);
			timer.cancel();
			this.dispose();
			return ;
		}
		if(0 != tarProcess.getCount()){
			int done = tarProcess.getDone() ;
			int count  = tarProcess.getCount() ;
			if(null == progress){ newProgress(count) ; }
			if(done == count) { words.setText("已完成 ."); backbtn.setEnabled(true); candonebtn.setText("完成"); } else { if(done>0){ words.setText("正在打包...");  }else{words.setText("正在准备...");}  backbtn.setEnabled(false); candonebtn.setText("取消");  }
			progress.setValue(done); // 随着线程进行，增加进度条值
			float value = (float) done /tarProcess.getCount() ;
	        progress.setString((new DecimalFormat("0.0000").format(value*100)) + "%");
	        hasdonenum.setText(done+"");
	        allnum.setText(count+"");
	        System.out.println("done  + "+tarProcess.getDone()+"  count = "+tarProcess.getCount()+" value  ="+(new DecimalFormat("0.0000").format(value*100)) + "%");
		}	
//			if(null == progress){ newProgress(tarProcess.getCount()) ; }
//			progress.setValue(progress.getValue()+1); // 随着线程进行，增加进度条值
//			float value = (float) progress.getValue() /progress.getMaximum() ;
//	        progress.setString((new DecimalFormat("0.0000").format(value*100)) + "%");
//	        System.out.println("progress.getValue()  = "+progress.getValue()+"  value = "+(new DecimalFormat("0.0000").format(value*100)) + "%");
	}
	
}
