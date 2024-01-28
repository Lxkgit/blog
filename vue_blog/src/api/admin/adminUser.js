import service from '../../plugins/axios'

export function updateUserStatus(user) {
  return service('/admin/user/status', user, 'post')
}
export function updateUserPassword(user) {
  return service('/admin/user/password', user, 'post')
}
export function updateUserMessage(user) {
  return service('/admin/user/message', user, 'post')
}
export function selectUserList() {
  return service('/admin/user/list', null, 'get')
}
