import service from '../../plugins/axios'

// 标签接口
export function insertArticleLabel(articleLabel) {
  return service('/admin/l/insert', articleLabel, 'post')
}
export function deleteArticleLabel(id) {
  return service('/admin/l/delete/' + id, null, 'delete')
}
export function updateArticleLabel(articleLabel) {
  return service('/admin/l/update', articleLabel, 'post')
}
export function selectArticleLabelList() {
  return service('/admin/l/select/list', null, 'get')
}
export function selectArticleLabelById(id) {
  return service('/admin/l/select/' + id, null, 'get')
}
export function selectArticleLabelByLabelTypeId(id) {
  return service('/admin/l/select/list/' + id, null, 'get')
}

// 标签分类接口
export function insertArticleLabelType(articleLabelType) {
  return service('/admin/label/type/insert', articleLabelType, 'post')
}
export function deleteArticleLabelType(id) {
  return service('/admin/label/type/delete/' + id, null, 'delete')
}
export function updateArticleLabelType(articleLabelType) {
  return service('/admin/label/type/update', articleLabelType, 'post')
}
export function selectArticleLabelTypeList() {
  return service('/admin/label/type/select/list', null, 'get')
}
export function selectArticleLabelTypeAndLabelList() {
  return service('/admin/label/type/select/label/list', null, 'get')
}
export function selectArticleLabelTypeById(id) {
  return service('/admin/label/type/select/'+ id, null, 'get')
}
export function selectArticleLabelTypeAndLabelById(id) {
  return service('/admin/label/type/select/label/'+ id, null, 'get')
}
