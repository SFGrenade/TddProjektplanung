package tdd.projektplanung;

public class EmployeeInProject {
	protected Employee employee;
	protected boolean productOwner;
	protected boolean scrumMaster;
	protected boolean backendDeveloper;
	protected boolean frontendDeveloper;

	public boolean isProductOwner() {
		return productOwner;
	}
	public void setProductOwner(boolean productOwner) {
		this.productOwner = productOwner;
	}
	public boolean isScrumMaster() {
		return scrumMaster;
	}
	public void setScrumMaster(boolean scrumMaster) {
		this.scrumMaster = scrumMaster;
	}
	public boolean isBackendDeveloper() {
		return backendDeveloper;
	}
	public void setBackendDeveloper(boolean backendDeveloper) {
		this.backendDeveloper = backendDeveloper;
	}
	public boolean isFrontendDeveloper() {
		return frontendDeveloper;
	}
	public void setFrontendDeveloper(boolean frontendDeveloper) {
		this.frontendDeveloper = frontendDeveloper;
	}
}
