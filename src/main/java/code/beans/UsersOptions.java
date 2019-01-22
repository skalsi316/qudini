package code.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersOptions implements Comparable<UsersOptions>  {

	private long id;
	private String name;
	private String duetime;	
	private String jointime;

    public UsersOptions() {
    }

    public UsersOptions(long id, String name, String duetime, String jointime) {
        this.id = id;
        this.name = name;
        this.duetime = duetime;
        this.jointime = jointime;
    }
 
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDuetime() {
		return duetime;
	}
	public void setDuetime(String duetime) {
		this.duetime = duetime;
	}

	public String getJointime() {
		return jointime;
	}
	public void setJointime(String jointime) {
		this.jointime = jointime;
	}
	
	@Override
	public int compareTo(UsersOptions ob) {
		return name.compareTo(ob.getDuetime());
	}
    
	@Override
    public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
        }
        final UsersOptions users = (UsersOptions) obj;
          if (this == users) {
             return true;
          } else {
             return (this.duetime == users.duetime);
          }
     }
	
    @Override
    public String toString() {
		return "[{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", duetime='" + duetime + '\'' +
				", jointime='" + jointime + '\'' + 
				"}]";
    }

}