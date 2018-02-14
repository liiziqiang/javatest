package com.mingrikeji;


import java.awt.*;
import java.awt.print.*;

public class StudyPrint {
	public static void main(String[] args) {
		try {
			PrinterJob job = PrinterJob.getPrinterJob();
			if (!job.printDialog())
				return;
			job.setPrintable(new Printable() {
				private final int OVAL_WIDTH = 130; // Բ�εĿ�
				private final int OVAL_HEIGHT = 130; // Բ�εĸ�
				
				public int print(Graphics graphics, PageFormat pageFormat,
						int pageIndex) throws PrinterException {
					if (pageIndex > 0)
						return Printable.NO_SUCH_PAGE;
					int x = (int) pageFormat.getImageableX();
					int y = (int) pageFormat.getImageableY();
					Graphics2D g2 = (Graphics2D) graphics;
					g2.setStroke(new BasicStroke(4.0F));
					g2.setColor(Color.BLUE);
					// ���Ƶ�1��Բ��
					g2.drawOval(x + 10, y + 10, OVAL_WIDTH, OVAL_HEIGHT); 
					g2.setColor(Color.CYAN);
					// ���Ƶ�2��Բ��
					g2.drawOval(x + 130, y + 10, OVAL_WIDTH, OVAL_HEIGHT); 
					g2.setColor(Color.GREEN);
					// ���Ƶ�3��Բ��
					g2.drawOval(x + 250, y + 10, OVAL_WIDTH, OVAL_HEIGHT); 
					g2.setColor(Color.MAGENTA);
					// ���Ƶ�4��Բ��
					g2.drawOval(x + 70, y + 120, OVAL_WIDTH, OVAL_HEIGHT); 
					g2.setColor(Color.ORANGE);
					// ���Ƶ�5��Բ��
					g2.drawOval(x + 190, y + 120, OVAL_WIDTH, OVAL_HEIGHT); 
					return Printable.PAGE_EXISTS;
				}
			});
			job.setJobName("��ӡͼ��");
			job.print();
		} catch (PrinterException e) {
			e.printStackTrace();
		}
	}
}