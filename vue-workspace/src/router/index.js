import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "@/views/HomeView.vue";
import TripView from "@/views/TripView.vue";
import MemberView from "@/views/MemberView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/trip",
    name: "trip",
    redirect: "trip/list",
    component: TripView,
    children: [
      {
        path: "list",
        name: "tripList",
        component: () => import("@/components/trip/TripList.vue"),
      },
      {
        path: "info",
        name: "tripInfo",
        component: () => import("@/components/trip/TripInfo.vue"),
      },
      {
        path: "board",
        name: "tripBoard",
        component: () => import("@/components/trip/TripBoard.vue"),
      },
    ],
  },
  {
    path: "/member",
    name: "member",
    component: MemberView,
    children: [
      {
        path: "login",
        name: "memberLogin",
        component: () => import("@/components/member/MemberLogin.vue"),
      },
      {
        path: "regist",
        name: "memberRegist",
        component: () => import("@/components/member/MemberRegist.vue"),
      },
      {
        path: "info",
        name: "memberInfo",
        component: () => import("@/components/member/MemberInfo.vue"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
