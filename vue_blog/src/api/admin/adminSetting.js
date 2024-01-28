import service from '../../plugins/axios'

export function selectWebSetting() {
  return service('/admin/setting/select/list', null, 'get')
}
export function selectSettingByKey(key) {
  return service('/setting/select/' + key, null, 'get')
}
export function updateValueByKey(data) {
  return service('/admin/setting/update/value', data, 'post')
}
