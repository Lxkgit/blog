import axios from 'axios'
// 对axios函数进行封装，用来发api请求，post使用qs进行处理，避免自己把from数据转换为json字符串
export default async function service (api, data, type) {
  // 默认数据
  var url = 'http://localhost:8095' + api
  // var url = 'http://1.116.112.250:8095/blog-1.0.1' + api
  const res = {
    status: 450,
    data: {
      message: '发送请求失败'
    }
  }
  // 判断请求类型
  if (type === 'get') {
    return await axios.get(url, { params: data })
  } else if (type === 'post') {
    return await axios.post(url, data)
  } else if (type === 'put') {
    return axios.put(url, data);
  } else if (type === 'delete') {
    return await axios.delete(url, {
      params: data
    })
  } else if (type === 'patch') {
    return await axios.patch(url, data)
  } else {
    return res
  }
}
