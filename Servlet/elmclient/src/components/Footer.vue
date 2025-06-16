<template>
  <ul class="footer">
    <li @click="toIndex">
      <i class="fa fa-home"></i>
      <p>首页</p>
    </li>
    <li @click="toStar">
      <i class="fa fa-compass"></i>
      <p>收藏</p>
    </li>
    <li @click="checkLogin('orderList')">
      <i class="fa fa-file-text-o"></i>
      <p>订单</p>
    </li>
    <li @click="checkLogin('profile')">
      <i class="fa fa-user-o"></i>
      <p>我的</p>
    </li>
  </ul>
</template>

<script>
export default {
  name: 'Footer',
  methods: {
    toIndex() {
      this.$router.push({path: '/index'});
    },
    toStar() {
      this.$router.push({path: '/star'});
    },

    // 修改后的检查登录方法 - 与BusinessList保持一致
    checkLogin(target) {
      // 检查sessionStorage中是否有用户信息
      const user = sessionStorage.getItem('user');

      // 如果未登录，跳转到登录页
      if (!user) {
        this.$router.push({path: '/Login'});
        return;
      }

      // 已登录用户跳转到目标页面
      if (target === 'orderList') {
        this.$router.push({path: '/orderList'});
      } else if (target === 'discovery') {
        // 此处应替换为真实的发现页面路由
        this.$router.push({path: '/discovery'});
      } else if (target === 'profile') {
        // 此处应替换为真实的个人中心页面路由
        this.$router.push({path: '/profile'});
      }
    }
  }
}
</script>

<style>
.footer {
  width: 100%;
  height: 14vw;
  border-top: 1px solid #ddd;
  background-color: #fff;
  position: fixed;
  left: 0;
  bottom: 0;
  display: flex;
  justify-content: space-around;
  align-items: center;
  z-index: 1000;
}

.footer ul {
  width: 100%;
  display: flex;
  list-style: none;
  margin: 0;
  padding: 0;
}

.footer li {
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  color: #999;
  padding: 1vw 0;
  cursor: pointer;
  transition: all 0.3s ease;
}

.footer li.active {
  color: #0097FF;
}

.footer li.active i {
  color: #0097FF;
}

.footer li.active p {
  color: #0097FF;
}

.footer li i {
  font-size: 5vw;
  margin-bottom: 0.5vw;
}

.footer li p {
  font-size: 2.8vw;
  margin: 0;
}
</style>