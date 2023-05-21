import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "@/views/HomeView.vue";
import TripView from "@/views/TripView.vue";
import MemberView from "@/views/MemberView.vue";
import BoardView from "@/views/BoardView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {  // 여행
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
  {  // 회원
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
  {  // 게시판
    path: "/board",
    name: "board",
    redirect: "board/free",
    component: BoardView,
    children: [
      {
        path: ":type",
        name: "boardList",
        component: () => import("@/components/board/BoardList.vue")
      },
      {
        path: ":type/:code",
        name: "boardInfo",
        component: () => import("@/components/board/BoardInfo.vue")
      },
      {
        path: ":type/:code/modify",
        name: "boardModify",
        component: () => import("@/components/board/BoardUpdate.vue")
      },
      {
        path: ":type/:code/write",
        name: "boardWrite",
        component: () => import("@/components/board/BoardUpdate.vue")
      }
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
