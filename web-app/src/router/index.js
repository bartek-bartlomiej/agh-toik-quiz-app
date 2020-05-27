import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import AbstractView from '../views/AbstractView'
import Quiz from '../views/Quiz'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '',
    component: AbstractView,
    children: [
      {
        path: '/quiz',
        name: 'Quiz',
        component: Quiz,
        beforeEnter: (to, from, next) => {
          if (typeof to.params.categoryId === 'undefined') { // TODO more precise validation
            next({ name: 'Home', replace: true })
          }
          next()
        }
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
