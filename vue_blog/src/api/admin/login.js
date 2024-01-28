import service from '../../plugins/axios'

// 登陆
export function login(data) { return service('/user/login', data, 'post')}
export function logout() { return service('/user/logout', null, 'get')}
export function register(user) { return service('/user/register', user, 'post')}
export function checkCode(code) { return service('/user/checkCode', code, 'get')}
export function auth() { return service('/user/authentication', null, 'get')}
