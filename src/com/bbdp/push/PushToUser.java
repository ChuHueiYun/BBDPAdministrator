package com.bbdp.push;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.json.JSONArray;

public class PushToUser {
	public static String pushToAPatient(DataSource datasource, String patientID, String title, String body, String hyperlink) {
		Connection con = null;
		String result = "";
		try {
		    con = datasource.getConnection();
		    Statement statement = con.createStatement();
		    ResultSet resultSet = statement.executeQuery("SELECT name FROM patient WHERE patientID = '" + patientID + "'");
		    if (resultSet.next()) {
		    	PushToFCM.sendNotification(title, body, patientID, hyperlink);
		    	result = "{\"state\":\"aPatient\",\"message\":\"發送成功\"}";
		    } else {
		    	result = "{\"state\":\"aPatient\",\"message\":\"沒有patientID " + patientID + " 這位病患\"}";
		    }
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println("BBDPAdministrator PushToUser pushToAPatient SQLException: " + e);
	    	result = "{\"state\":\"aPatient\",\"message\":\"出現 SQLException\"}";
		} finally {
			if (con != null) try {con.close();}catch (Exception ignore) {}
		}
		return result;
	}
	public static String pushToAllPatient(DataSource datasource, String title, String body, String hyperlink) {
		Connection con = null;
		String result = "";
		try {
		    con = datasource.getConnection();
		    Statement statement = con.createStatement();
		    ResultSet resultSet = statement.executeQuery("SELECT patientID FROM patient");
		    while (resultSet.next()) {
		    	PushToFCM.sendNotification(title, body, resultSet.getString("patientID"), hyperlink);
		    }
	    	result = "{\"state\":\"allPatient\",\"message\":\"發送成功\"}";
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println("BBDPAdministrator PushToUser pushToAPatient SQLException: " + e);
	    	result = "{\"state\":\"allPatient\",\"message\":\"出現 SQLException\"}";
		} finally {
			if (con != null) try {con.close();}catch (Exception ignore) {}
		}
		return result;
	}
	public static String pushToADoctor(DataSource datasource, String doctorID, String title, String body, String hyperlink) {
		Connection con = null;
		String result = "";
		try {
		    con = datasource.getConnection();
		    Statement statement = con.createStatement();
		    ResultSet resultSet = statement.executeQuery("SELECT name FROM doctor WHERE doctorID = '" + doctorID + "'");
		    if (!resultSet.next()) {
		    	result = "{\"state\":\"aDoctor\",\"message\":\"沒有doctorID " + doctorID + " 這位醫師\"}";
		    }
		    else {
		    	result = "{\"state\":\"aDoctor\",\"message\":\"success\"}";
		    }
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println("BBDPAdministrator PushToUser pushToAPatient SQLException: " + e);
	    	result = "{\"state\":\"aDoctor\",\"message\":\"出現 SQLException\"}";
		} finally {
			if (con != null) try {con.close();}catch (Exception ignore) {}
		}
		return result;
	}
	public static String pushToAllDoctor(DataSource datasource, String title, String body, String hyperlink) {
		Connection con = null;
		String result = "";
		JSONArray doctorIDs = new JSONArray();
		try {
		    con = datasource.getConnection();
		    Statement statement = con.createStatement();
		    ResultSet resultSet = statement.executeQuery("SELECT doctorID FROM doctor");
		    while (resultSet.next()) {
		    	doctorIDs.put(resultSet.getString("doctorID"));
		    }
		    result = "{\"state\":\"allDoctor\",\"message\":\"success\",\"doctorIDs\":" + doctorIDs.toString() + "}";
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println("BBDPAdministrator PushToUser pushToAPatient SQLException: " + e);
	    	result = "{\"state\":\"allDoctor\",\"message\":\"出現 SQLException\"}";
		} finally {
			if (con != null) try {con.close();}catch (Exception ignore) {}
		}
		return result;
	}
}