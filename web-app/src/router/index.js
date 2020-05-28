import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import AbstractView from '../views/AbstractView'
import Quiz from '../views/Quiz'
import { QuizQueryDTO } from '../api/model'
import Categories from '../views/Categories'

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
          const query = QuizQueryDTO.parseParameters(to.params)
          if (typeof query.category === 'undefined' || typeof query.difficulty === 'undefined' || typeof query.quantity !== 'number') {
            next({ name: 'Home', replace: true })
          }
          next()
        }
      },
      {
        path: '/categories',
        name: 'Categories',
        component: Categories
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
