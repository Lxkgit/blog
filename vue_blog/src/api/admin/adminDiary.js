import service from "../../plugins/axios";

export function insertDiary(diary) {
  return service('/admin/diary/insert',diary, 'post')
}
export function deleteDiary(id) {
  return service('/admin/diary/delete/id/' + id, null, 'delete')
}
export function updateDiary(diary) {
  return service('/admin/diary/update', diary, 'post')
}
export function selectDiaryList(data) {
  return service('/admin/diary/select/list/' + data.size + '/' + data.page, null, 'get')
}
export function selectDiaryDetail(date) {
  return service('/admin/diary/select/' + date, null, 'get')
}
