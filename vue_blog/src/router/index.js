import Vue from 'vue'
import Router from 'vue-router'
import HomePage from '../components/home/HomePage'
import Login from '../components/login/Login'
import Register from '../components/login/Register'
import AdminIndex from '../components/admin/AdminIndex'

Vue.use(Router)

export default new Router({
  mode: 'hash',
  routes: [
    {
      path: '/',
      name: 'Default',
      redirect: '/index',
      component: HomePage
    },
    {
      path: '/admin/content/editor',
      name: 'Editor',
      component: () => import('../components/admin/content/blog/editor/BlogEditor'),
      meta: {
        requireAuth: true
      }
    },
    {
      path: '/homepage',
      name: 'HomePage',
      component: HomePage,
      redirect: '/index',
      children: [
        {
          path: '/index',
          name: 'HomeIndex',
          component: () => import('../components/home/home_index/HomeIndex')
        },
        {
          path: '/nav',
          name: 'NavIndex',
          component: () => import('../components/home/navigation/NavIndex')
        },
        {
          path: '/doc',
          name: 'DocIndex',
          component: () => import('../components/home/doc/DocIndex')
        },
        {
          path: '/blog',
          name: 'Blog',
          component: () => import('../components/home/type/blog/BlogIndex')
        },
        {
          path: '/blogDetail',
          name: 'blogDetail',
          component: () => import('../components/home/type/blog/BlogDetailsIndex')
        },
        {
          path: '/game',
          name: 'GamePage',
          component: () => import('../components/home/type/game/GameIndex')
        }
      ]
    },
    {
      path: '/admin',
      name: 'Admin',
      component: AdminIndex,
      meta: {
        requireAuth: true
      },
      children: [
        {
          path: '/admin/index',
          name: 'Index',
          component: () => import('../components/admin/dashboard/index'),
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/admin/content/diary/editor',
          name: 'TinyMode',
          component: () => import('../components/admin/content/diary/TinyMode'),
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/admin/content/update/editor',
          name: 'TinyModeUpdate',
          component: () => import('../components/admin/content/update/TinyModeUpdate'),
          meta: {
            requireAuth: true
          }
        }
      ]
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: 'error',
      component: () => import('../components/error/Error404')
    },
    // {
    //   path: '*',
    //   component: () => import('../components/error/Error404')
    // }
  ]
})

// 用于创建默认路由
export const createRouter = routes => new Router({
  mode: 'hash',
  routes: [
    {
      path: '/',
      name: 'Default',
      redirect: '/homepage',
      component: HomePage
    },
    {
      path: '/admin/content/editor',
      name: 'Editor',
      component: () => import('../components/admin/content/blog/editor/BlogEditor'),
      meta: {
        requireAuth: true
      }
    },
    {
      path: '/homepage',
      name: 'HomePage',
      component: HomePage,
      redirect: '/index',
      children: [
        {
          path: '/index',
          name: 'HomeIndex',
          component: () => import('../components/home/home_index/HomeIndex')
        },
        {
          path: '/nav',
          name: 'NavIndex',
          component: () => import('../components/home/navigation/NavIndex')
        },
        {
          path: '/doc',
          name: 'DocIndex',
          component: () => import('../components/home/doc/DocIndex')
        },
        {
          path: '/blog',
          name: 'Blog',
          component: () => import('../components/home/type/blog/BlogIndex')
        },
        {
          path: '/blogDetail',
          name: 'blogDetail',
          component: () => import('../components/home/type/blog/BlogDetailsIndex')
        }
      ]
    },
    {
      path: '/admin',
      name: 'Admin',
      component: AdminIndex,
      redirect: '/admin/index',
      children: [
        {
          path: '/admin/index',
          name: 'index',
          component: () => import('../components/admin/dashboard/index')
        },
        {
          path: '/admin/content/diary/editor',
          name: 'TinyMode',
          component: () => import('../components/admin/content/diary/TinyMode'),
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/admin/content/update/editor',
          name: 'TinyModeUpdate',
          component: () => import('../components/admin/content/update/TinyModeUpdate'),
          meta: {
            requireAuth: true
          }
        }
      ]
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: 'error',
      component: () => import('../components/error/Error404')
    },
    {
      path: '*',
      component: () => import('../components/error/Error404')
    }
  ]
})
