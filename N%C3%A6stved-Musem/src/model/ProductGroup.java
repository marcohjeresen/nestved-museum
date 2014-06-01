
package model;


public class ProductGroup {
    private int groupId;
    private String groupType;

    /**
    * Constructor, creates a new object of the class.
    */
    
    public ProductGroup(int groupId, String groupType) {
        this.groupId = groupId;
        this.groupType = groupType;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    @Override
    public String toString() {
        return "groupId: " + groupId + " groupType: " + groupType;
    }
    
}
