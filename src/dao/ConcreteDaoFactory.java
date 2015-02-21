package dao;

public class ConcreteDaoFactory extends DaoFactory {

	private ConcreteAccDAO concreteAccDAO;
	private ConcreteProjectDAO concreteProjectDAO;
	private ConcreteTaskListDAO concreteTaskDAO;
	private ConcreteProjectVersionDAO concreteProjectVersionDAO;
	private ConcretePrivateTaskListDAO concretePrivateTaskListDAO;
	private ConcreteTimeSheetDAO concreteTimeSheetDAO;
	private ConcreteTimestampDAO concreteTimestampDAO;
	private ConcretePrivilegeitemDAO concretePrivilegeitemDAO;
	private ConcreteDepartmentitemDAO concreteDepartmentitemDAO;
	
	public ConcreteDaoFactory() {

	}

	@Override
	public DAO createDAO(String type) {
		if (type.equals("userDAO")) {
			if (concreteAccDAO == null)
				concreteAccDAO = new ConcreteAccDAO(em);
			return concreteAccDAO;
		} else if (type.equals("projectDAO")) {
			if (concreteProjectDAO == null)
				concreteProjectDAO = new ConcreteProjectDAO(em);
			return concreteProjectDAO;
		} else if (type.equals("taskDAO")) {
			if (concreteTaskDAO == null)
				concreteTaskDAO = new ConcreteTaskListDAO(em);
			return concreteTaskDAO;
		} else if (type.equals("projectVersionDAO")) {
			if (concreteProjectVersionDAO == null)
				concreteProjectVersionDAO = new ConcreteProjectVersionDAO(em);
			return concreteProjectVersionDAO;
		} else if (type.equals("privateTaskListDAO")) {
			if (concretePrivateTaskListDAO == null)
				concretePrivateTaskListDAO = new ConcretePrivateTaskListDAO(em);
			return concretePrivateTaskListDAO;
		} else if (type.equals("timesheetDAO")) {
			if (concreteTimeSheetDAO == null)
				concreteTimeSheetDAO = new ConcreteTimeSheetDAO(em);
			return concreteTimeSheetDAO;
		} else if (type.equals("timestampDAO")) {
			if (concreteTimestampDAO == null)
				concreteTimestampDAO = new ConcreteTimestampDAO(em);
			return concreteTimestampDAO;
		} else if(type.equals("privilegeitemDAO")){
			if (concretePrivilegeitemDAO == null)
				concretePrivilegeitemDAO = new ConcretePrivilegeitemDAO(em);
			return concretePrivilegeitemDAO;
		} else if(type.equals("departmentitemDAO")){
			if (concreteDepartmentitemDAO == null)
				concreteDepartmentitemDAO = new ConcreteDepartmentitemDAO(em);
			return concreteDepartmentitemDAO;
		}
		return null;
	}
}
