import service from '../../plugins/axios'

export function uploadImg(data) {
  return service('/admin/file/upload/img', data, 'post')
}
