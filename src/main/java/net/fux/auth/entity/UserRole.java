package net.fux.auth.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * Created by fuxj on 2019/3/6
 */
@Entity
@Table(name="sys_user_role")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="role_id")
	private int roleId;

	@Column(name="user_id")
	private int userId;

	public UserRole() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}