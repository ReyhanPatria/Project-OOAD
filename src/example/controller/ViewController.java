package example.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NoSuchObjectException;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import example.session.Session;
import example.view.FirstPage;
import example.view.LoginPanel;
import example.view.MainFrame;
import example.view.MenuAdminView;
import example.view.ProfileAdminView;
import example.view.RegisterUserPanel;

public class ViewController {
	// STATIC FUNCTIONS
	// Loads start up page
	public static FirstPage loadFirstPage() {
		FirstPage fp = new FirstPage();
		
		// Logic for login button
		fp.getLoginButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewController.loadLoginPanel();
			}
		});
		
		FrameController.changePanel(fp);
		
		return fp;
	}
	
	// Loads login page
	public static LoginPanel loadLoginPanel() {
		LoginPanel lp = new LoginPanel();
		
		// Logic for clicking login button
		lp.getLoginButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = lp.getUsernameTextField().getText();
				String password = String.valueOf(lp.getPasswordTextField().getPassword());
				
				try {
					UserController.login(username, password);
					
					ViewController.loadMenuView();
				}
				catch(IllegalArgumentException e1) {
					lp.getLbl_Invalid().setVisible(true);
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		// Logic for clicking back button
		lp.getBackButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewController.loadFirstPage();
			}
		});
		
		FrameController.changePanel(lp);
		
		return lp;
	}
	
	// Function to decide which menu to load after login
	public static void loadMenuView() {
		try {
			String userRole = Session.getInstance().getCurrentUser().getRole();
			
			if(userRole.equalsIgnoreCase("admin")) {
				ViewController.loadMenuAdminView();
			}
			else if(userRole.equalsIgnoreCase("supervisor")) {
				/*
				 * TODO
				 */
			}
			else if(userRole.equalsIgnoreCase("worker")) {
				/*
				 * TODO
				 */
			}
		}
		catch(NoSuchObjectException e) {
			e.printStackTrace();
		}
	}
	
	// Loads menu for admin
	public static MenuAdminView loadMenuAdminView() {
		MenuAdminView mav = new MenuAdminView();
		
		// Logic for register button
		mav.getRegisterButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewController.loadRegisterUserPanel();
			}
		});
		
		// Logic for view all user button
		mav.getViewButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * 
				 * 
				 * TODO
				 * 
				 * 
				 */
			}
		});
		
		// Logic for logout button
		mav.getLogOutButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserController.logout();
				ViewController.loadFirstPage();
			}
		});
		
		FrameController.changePanel(mav);
		
		return mav;
	}
	
	// Loads register user panel
	public static RegisterUserPanel loadRegisterUserPanel() {
		RegisterUserPanel rp = new RegisterUserPanel();
		
		rp.getRegisterButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String 	username 		= 	rp.getUsernameTextField().getText();
				String 	role 			= 	rp.getRoleComboBox().getSelectedItem().toString();
				String 	address 		= 	rp.getAddressTextField().getText();
				Date 	DOB 			= 	new Date(rp.getDOBDateChooser().getDateEditor().getDate().getTime());
				String 	telp 			= 	rp.getTelpTextField().getText();
				
				try {
					UserController.registerUser(username, role, address, DOB, telp);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(), e1.getMessage());
				}
			}
		});
		
		rp.getProfileButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewController.loadProfileView();
			}
		});
		
		rp.getCancelButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewController.loadMenuView();
			}
		});	
		
		FrameController.changePanel(rp);
		
		return rp;
	}
	
	public static void loadProfileView() {
		try {
			String userRole = Session.getInstance().getCurrentUser().getRole();
			
			if(userRole.equalsIgnoreCase("admin")) {
				ViewController.loadProfileAdminView();
			}
			else if(userRole.equalsIgnoreCase("supervisor")) {
				/*
				 * TODO
				 */
			}
			else if(userRole.equalsIgnoreCase("worker")) {
				/*
				 * TODO
				 */
			}
		}
		catch(NoSuchObjectException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadProfileAdminView() {
		ProfileAdminView pav = new ProfileAdminView();
		
		// Logic for home buttom
		pav.getHomeButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewController.loadMenuView();
			}
		});
		
		// Logic for change password button
		pav.getChangePasswordButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * 
				 * TODO
				 * 
				 */
			}
		});
		
		// Logic for edit profile button
		pav.getEditProfileButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * 
				 * TODO
				 * 
				 */
			}
		});
		
		FrameController.changePanel(pav);
	}
}
