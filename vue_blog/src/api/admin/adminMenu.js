import service from '../../plugins/axios'

export function selectCurrentMenu() {
  return service('/admin/menu', null, 'get')
}
export function selectAllMenu() {
  return service('/admin/role/menu', null, 'get')
}
