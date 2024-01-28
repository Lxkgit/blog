import service from "../../plugins/axios";

export function insertUpdate(update) {
  return service('/admin/update/insert', update, 'post')
}
export function deleteUpdate(id) {
  return service('/admin/update/delete/' + id, null, 'delete')
}
export function updateUpdate(update) {
  return service('/admin/update/update', null, 'post')
}
export function selectUpdateList(data) {
  return service('/admin/update/select/list/' + data.size + '/' + data.list, null, 'get')
}
