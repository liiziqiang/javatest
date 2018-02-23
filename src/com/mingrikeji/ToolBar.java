package com.lzw;

import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import com.lzw.dao.*;

public class ToolBar extends JToolBar {
	private MenuBar menuBar;
	/**
	 * 默认的构造方法
	 */
	private ToolBar() {
	}

	public ToolBar(MenuBar frameMenuBar) {
		super();
		this.menuBar = frameMenuBar;
		initialize();
	}

	/**
	 * 界面初始化方法
	 * 
	 */
	private void initialize() {
		setSize(new Dimension(600, 24));
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		add(createToolButton(menuBar.getJinhuoItem()));
		add(createToolButton(menuBar.getXiaoshou_danItem()));
		add(createToolButton(menuBar.getKucun_pandianItem()));
		add(createToolButton(menuBar.getJiage_tiaozhengItem()));
		add(createToolButton(menuBar.getShangpin_chaxunItem()));
		add(createToolButton(menuBar.getShangpin_guanliItem()));
		add(createToolButton(menuBar.getKehu_guanliItem()));
		add(createToolButton(menuBar.getGys_guanliItem()));
		add(createToolButton(menuBar.getExitItem()));
	}

	/**
	 * 创建工具栏按钮的方法
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton createToolButton(final JMenuItem item) {
		JButton button = new JButton();
		button.setText(item.getText());
		button.setToolTipText(item.getText());
		button.setIcon(item.getIcon());
		button.setFocusable(false);
		button.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				item.doClick();
			}
		});
		return button;
	}

	public void setMenuBar(MenuBar menuBar) {
		this.menuBar = menuBar;
	}
} // @jve:decl-index=0:visual-constraint="10,10"
