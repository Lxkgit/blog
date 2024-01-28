// 博客文章相关
import service from "../../plugins/axios";

export function insertBlog(blog) { return service('/admin/content/insert', blog, 'post')}
export function deleteBlog(id) { return service('/admin/content/delete/id/'+  id, null, 'delete')}
export function updateBlog(blog) { return service('/admin/content/update', blog, 'post')}
export function selectBlogListAdmin(data) {return service('/admin/content/find/list/' + data.size + '/' + data.page , null, 'get')}
export function selectBlogDetailAdmin(id) { return service('/admin/content/find/id/' + id, null, 'get')}
