import service from '../../plugins/axios'

export function insertArticleType(articleType) {
  return service('/admin/label/insert', articleType, 'post')
}
export function deleteArticleType(id) {
  return service('/admin/label/delete/' + id, null, 'delete')
}
export function updateArticleType(articleType) {
  return service('/admin/label/update', articleType, 'post')
}
export function selectArticleTypeList() {
  return service('/admin/label/select/list', null, 'get')
}
export function selectArticleTypeTree() {
  return service('/admin/label/select/tree', null, 'get')
}
export function selectArticleTypeListByPage(data) {
  return service('/admin/label/select/list/' + data.size + '/' + data.page, null, 'get')
}
