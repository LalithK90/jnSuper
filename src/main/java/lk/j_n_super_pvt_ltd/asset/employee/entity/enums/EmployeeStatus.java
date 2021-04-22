package lk.j_n_super_pvt_ltd.asset.employee.entity.enums;

public enum EmployeeStatus {
    WORKING("Working"),
    LEAVE("Leave"),
    SUSPENDED("Suspended"),
    RESIGNED("Resigned"),
    RETIRED("Retired");

    private final String employeeStatus;

    EmployeeStatus(String employeeStatus) {

        this.employeeStatus = employeeStatus;
    }

    public String getEmployeeStatus() {
        return employeeStatus;
    }
}
