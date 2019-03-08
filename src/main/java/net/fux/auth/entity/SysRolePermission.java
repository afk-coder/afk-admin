package net.fux.auth.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Created by fuxj on 2019/3/6
 */
@Entity
@Table(name="sys_role_permission")
public class SysRolePermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="permission_id")
	private int permissionId;

	@Column(name="role_id")
	private int roleId;

	public SysRolePermission() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}