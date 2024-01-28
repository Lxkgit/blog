import service from '../../plugins/axios'

export function insertRole(role) {
  return service('/admin/role/add', role, 'post')
}
export function updateRoleStatus(role) {
  return service('/admin/role/status', role, 'put')
}
export function updateRolePermission(role) {
  return service('/admin/role/message', role, 'put')
}
export function updateRoleMenu(arr) {
  return service('/admin/role/update/menu?rid=' + arr.roleId, arr.menusIds, 'put')
}
export function selectPermsAndMenus() {
  return service('/admin/role/list', null, 'get')
}
export function selectPermissionList() {
  return service('/admin/role/perm', null, 'get')
}
