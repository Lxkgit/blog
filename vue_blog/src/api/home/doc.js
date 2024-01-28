import service from '../../plugins/axios'

// 博客文档系统相关 无权限查看接口
export function selectDocList() { return service('/doc/select/list', null, 'get')}
export function selectDocContentById(id) {return service('/doc/select/content/' + id, null, 'get')}
// 有权限管理接口
export function insertDoc(data) {return service('/admin/doc/insert/doc', data, 'post')}
export function deleteDoc(id) { return service('/admin/doc/delete/doc/' + id, null, 'delete')}
export function updateDocCatalog(data) { return service('/admin/doc/update/catalog', data, 'post')}
export function updateDocContent(data) { return service('/admin/doc/update/content', data, 'post')}
