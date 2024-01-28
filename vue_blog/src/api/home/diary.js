import service from "../../plugins/axios";

export function selectDiaryListHome(data) {
  return service('/diary/select/list/' + data.size + '/' + data.page, null, 'get')
}
export function selectDiaryByDateHome(date) {
  return service('/diary/select/' + date, null, 'get')
}
