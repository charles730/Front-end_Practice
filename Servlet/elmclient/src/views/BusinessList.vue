<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>商家列表</p>
    </header>
    <!-- 商家列表部分 -->
    <ul class="business">
      <li v-for="item in businessArr" @click="toBusinessInfo(item.businessId)">
        <div class="business-img">
          <img :src="item.businessImg">
          <div class="business-img-quantity" v-show="item.quantity>0">
            {{ item.quantity }}
          </div>
        </div>
        <div class="business-info">
          <h3>{{ item.businessName }}</h3>
          <p>&#165;{{ item.starPrice }}起送 | &#165;{{ item.deliveryPrice }}配送</p>
          <p>{{ item.businessExplain }}</p>
        </div>
        <!-- 收藏按钮 - 使用Font Awesome图标 -->
        <div class="favorite-btn" @click.stop="toggleFavorite(item.businessId, item.isStarred)">
          <i :class="['fa', item.isStarred ? 'fa-heart favorite-red' : 'fa-heart-o', 'favorite-icon']"></i>
        </div>
      </li>
    </ul>
    <!-- 底部菜单部分 -->
    <Footer></Footer>
  </div>
</template>

<script>
import Footer from '../components/Footer.vue';
import Vue from 'vue'; // 引入Vue用于响应式处理

export default {
  name: 'BusinessList',
  data() {
    return {
      orderTypeId: this.$route.query.orderTypeId,
      businessArr: [],
      user: {}
    }
  },
  created() {
    this.user = this.$getSessionStorage('user');
    this.loadBusinessList();
  },
  components: {
    Footer
  },
  methods: {
    // 加载商家列表
    loadBusinessList() {
      this.$axios.post('BusinessController/listBusinessByOrderTypeId', this.$qs.stringify({
        orderTypeId: this.orderTypeId
      })).then(response => {
        this.businessArr = response.data;
        // 判断是否登录
        if (this.user.userId) {
          this.loadCartQuantity();
          this.checkFavoriteStatus();
        }
      }).catch(error => {
        console.error('获取商家列表失败', error);
      });
    },

    // 加载购物车数量
    loadCartQuantity() {
      this.$axios.post('CartController/listCart', this.$qs.stringify({
        userId: this.user.userId
      })).then(response => {
        const cartArr = response.data;
        for (const businessItem of this.businessArr) {
          businessItem.quantity = 0;
          for (const cartItem of cartArr) {
            if (cartItem.businessId === businessItem.businessId) {
              businessItem.quantity += cartItem.quantity;
            }
          }
        }
      }).catch(error => {
        console.error('加载购物车数量失败', error);
      });
    },

    // 检查收藏状态（使用Vue.set确保响应式）
    checkFavoriteStatus() {
      for (const business of this.businessArr) {
        this.$axios.post('BusinessController/isBusinessStarredById', this.$qs.stringify({
          businessId: business.businessId,
          userId: this.user.userId
        })).then(response => {
          // 使用Vue.set确保响应式更新
          Vue.set(business, 'isStarred', response.data === 1);
        }).catch(error => {
          console.error('检查收藏状态失败', error);
          Vue.set(business, 'isStarred', false);
        });
      }
    },

    // 切换收藏状态
    toggleFavorite(businessId, isStarred) {
      if (!this.user.userId) {
        this.$router.push('/login');
        return;
      }

      const business = this.businessArr.find(item => item.businessId === businessId);
      if (!business) return;

      // 先更新本地状态（使用Vue.set确保响应式）
      Vue.set(business, 'isStarred', !isStarred);

      if (isStarred) {
        // 取消收藏
        this.$axios.post('BusinessController/unstarBusinessById', this.$qs.stringify({
          businessId: businessId,
          userId: this.user.userId
        })).then(response => {
          if (response.data !== 1) {
            // 接口失败时恢复状态
            Vue.set(business, 'isStarred', true);
            alert('取消收藏失败');
          } else {
            alert('已取消收藏');
          }
        }).catch(error => {
          console.error('取消收藏接口调用失败', error);
          // 网络错误时恢复状态
          Vue.set(business, 'isStarred', true);
          alert('操作失败，请重试');
        });
      } else {
        // 添加收藏
        this.$axios.post('BusinessController/starBusinessById', this.$qs.stringify({
          businessId: businessId,
          userId: this.user.userId
        })).then(response => {
          if (response.data !== 1) {
            // 接口失败时恢复状态
            Vue.set(business, 'isStarred', false);
            alert('收藏失败');
          } else {
            alert('收藏成功');
          }
        }).catch(error => {
          console.error('收藏接口调用失败', error);
          // 网络错误时恢复状态
          Vue.set(business, 'isStarred', false);
          alert('操作失败，请重试');
        });
      }
    },

    // 跳转到商家详情
    toBusinessInfo(businessId) {
      this.$router.push({ path: '/businessInfo', query: { businessId: businessId } });
    }
  }
}
</script>

<style scoped>
/****************** 总容器 ******************/
.wrapper {
  width: 100%;
  height: 100%;
}

/****************** header部分 ******************/
.wrapper header {
  width: 100%;
  height: 12vw;
  background-color: #0097FF;
  color: #fff;
  font-size: 4.8vw;
  position: fixed;
  left: 0;
  top: 0;
  z-index: 1000;
  display: flex;
  justify-content: center;
  align-items: center;
}

/****************** 商家列表部分 ******************/
.wrapper .business {
  width: 100%;
  margin-top: 12vw;
  margin-bottom: 14vw;
}

.wrapper .business li {
  width: 100%;
  box-sizing: border-box;
  padding: 2.5vw;
  border-bottom: solid 1px #DDD;
  user-select: none;
  cursor: pointer;
  display: flex;
  align-items: center;
  position: relative;
}

.wrapper .business li .business-img {
  position: relative;
  margin-right: 3vw;
}

.wrapper .business li .business-img img {
  width: 20vw;
  height: 20vw;
  border-radius: 4px;
}

.wrapper .business li .business-img .business-img-quantity {
  width: 5vw;
  height: 5vw;
  background-color: red;
  color: #fff;
  font-size: 3.6vw;
  border-radius: 2.5vw;
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  right: -1.5vw;
  top: -1.5vw;
}

.wrapper .business li .business-info {
  flex: 1;
}

.wrapper .business li .business-info h3 {
  font-size: 3.8vw;
  color: #555;
  margin-bottom: 1vw;
}

.wrapper .business li .business-info p {
  font-size: 3vw;
  color: #888;
  margin-top: 0.5vw;
}

/* 收藏按钮样式 */
.wrapper .business li .favorite-btn {
  position: absolute;
  right: 3vw;
  top: 50%;
  transform: translateY(-50%);
  width: 8vw;
  height: 8vw;
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 10;
}

.wrapper .business li .favorite-icon {
  font-size: 5vw;
  transition: all 0.3s ease;
}

/* 已收藏状态 - 红色填充 */
.wrapper .business li .fa-heart {
  color: #ff4d4f;
}

/* 未收藏状态 - 灰色空心 */
.wrapper .business li .fa-heart-o {
  color: #999;
}

.wrapper .business li .favorite-btn:active .favorite-icon {
  transform: scale(0.9);
}

/* 点击动画 */
.wrapper .business li .favorite-btn i {
  transition: transform 0.3s ease, color 0.3s ease;
}

.wrapper .business li .favorite-btn:active i {
  transform: scale(0.9);
}

.wrapper .business li .favorite-btn i:hover {
  color: #ff7e5f; /* 悬停时颜色变深 */
}
</style>