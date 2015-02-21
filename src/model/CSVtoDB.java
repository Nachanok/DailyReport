package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.ConcreteDaoFactory;
import dao.ConcreteTimestampDAO;

public class CSVtoDB {

	private SimpleDateFormat myFormat = new SimpleDateFormat("MM/dd/yyyy");
	private ConcreteTimestampDAO timestampDAO;

	public CSVtoDB(String fileDir) {
		timestampDAO = (ConcreteTimestampDAO) ConcreteDaoFactory.getInstance().createDAO("timestampDAO");
		String line = "";
		List<String[]> rawData = new ArrayList<String[]>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileDir));
			br.readLine();
			while ((line = br.readLine()) != null) {
				line.replace("\"", "");
				String[] content = line.split(";");
				rawData.add(content);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Timestamp> lists = new ArrayList<Timestamp>();
		try {
			lists = translateRawData(rawData);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < lists.size(); i++) {
			timestampDAO.save(lists.get(i));// add to db
		}
	}

	public List<Timestamp> translateRawData(List<String[]> rawData) throws ParseException {
		List<Timestamp> lists = new ArrayList<Timestamp>();
		while (!rawData.isEmpty()) {
			Timestamp converted = new Timestamp();
			String[] data = rawData.remove(0);
			converted.setDepartment(data[0]);
			converted.setName(data[1]);
			TimestampPK id = new TimestampPK();
			id.setId(Integer.parseInt(data[2]));
			String[] timeInArray = data[3].split(" ");
			id.setDate(myFormat.parse(timeInArray[0]));
			converted.setId(id);
			Time timeIn = Time.valueOf(timeInArray[1]);
			if (timeInArray[2].equalsIgnoreCase("pm")) {
				String[] hourminsec = timeInArray[1].split(":");
				hourminsec[0] = (12 + (Integer.parseInt(hourminsec[0]))) + "";
				timeIn = Time.valueOf(hourminsec[0] + ":" + hourminsec[1] + ":" + hourminsec[2]);
			}
			converted.setTimeIn(timeIn);
			while (!rawData.isEmpty()) {
				String[] data2 = rawData.get(0);
				String[] timeOutArray = data2[3].split(" ");
				Date check = myFormat.parse(timeOutArray[0]);
				if (data[2].equals(data2[2]) && converted.getId().getDate().equals(check)) {
					if (timeOutArray[2].equalsIgnoreCase("pm")) {
						String[] hourminsec = timeOutArray[1].split(":");
						hourminsec[0] = (12 + (Integer.parseInt(hourminsec[0]))) + "";
						timeIn = Time.valueOf(hourminsec[0] + ":" + hourminsec[1] + ":" + hourminsec[2]);
					}
					converted.setTimeout(timeIn);
					rawData.remove(0);
				} else {
					break;
				}
			}
			lists.add(converted);
		}
		return lists;
	}
}
