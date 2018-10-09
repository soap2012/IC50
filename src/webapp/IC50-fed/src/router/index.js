import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'IC50Compute',
      component: () => import('@/page/IC50.compute/index')
    }
  ]
})
