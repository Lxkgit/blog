import service from '../../plugins/axios'

export function selectArticleTypeListHome() {
  return service('/label/select/type', null, 'get')
}
export function selectArticleTypeByIdHome(id) {
  return service('/label/select/type/' + id, null, 'get')
}
export function selectArticleTypeTreeHome() {
  return service('/label/select/tree', null, 'get')
}
