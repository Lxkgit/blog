import service from "../../plugins/axios";

export function selectUpdateList(data) {
  return service('/update/select/list/' + data.size + '/' + data.list, null, 'get')
}
