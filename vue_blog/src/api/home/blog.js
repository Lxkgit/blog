import service from '../../plugins/axios'

// 博客文章相关
export function selectBlogList(data) { return service('/article/find/list/' + data.size + '/' + data.page , null, 'get')}
export function selectBlogDetail(id, data) { return service('/article/find/id/' + id, data, 'get')}
