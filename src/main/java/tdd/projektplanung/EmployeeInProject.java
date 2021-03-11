package tdd.projektplanung;

public class EmployeeInProject {
	protected boolean productOwner;
	protected boolean scrumMaster;
	protected boolean backendDeveloper;
	protected boolean frontendDeveloper;
	protected int wantedHours;
	protected int trackedHours;
	
	public EmployeeInProject(boolean productOwner, boolean scrumMaster, boolean backendDeveloper, boolean frontendDeveloper, int wantedHours) {
		this.productOwner = productOwner;
		this.scrumMaster = scrumMaster;
		this.backendDeveloper = backendDeveloper;
		this.frontendDeveloper = frontendDeveloper;
		this.wantedHours = wantedHours;
		this.trackedHours = 0;
	}

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

	public int getWantedHours() {
		return wantedHours;
	}
	public void setWantedHours(int wantedHours) {
		this.wantedHours = wantedHours;
	}

	public int getTrackedHours() {
		return trackedHours;
	}
	public void setTrackedHours(int trackedHours) {
		this.trackedHours = trackedHours;
	}
	public void addTrackedHours(int trackedHours) {
		this.trackedHours += trackedHours;
	}
}
