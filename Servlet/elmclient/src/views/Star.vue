<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>我的收藏</p>
    </header>

    <!-- 内容区域 -->
    <div class="content-area">
      <!-- 空收藏背景提示 -->
      <div class="empty-background" v-if="favoriteArr.length === 0">
        <div class="empty-content">
          <i class="fa fa-heart-o empty-icon"></i>
          <p class="empty-title">暂无收藏的商家</p>
          <p class="empty-subtitle">点击商家卡片右上角❤️添加收藏</p>
        </div>
      </div>

      <!-- 收藏列表部分 -->
      <ul class="business" v-if="favoriteArr.length > 0">
        <li v-for="item in favoriteArr" :key="item.businessId" @click="toBusinessInfo(item.businessId)">
          <div class="business-img">
            <img :src="item.businessImg" alt="商家图片">
            <div class="business-img-quantity" v-show="item.quantity > 0">
              {{ item.quantity }}
            </div>
          </div>
          <div class="business-info">
            <h3 class="business-name">{{ item.businessName }}</h3>
            <p class="price-info">&#165;{{ item.starPrice }}起送 | &#165;{{ item.deliveryPrice }}配送</p>
            <p class="explain-info">{{ item.businessExplain }}</p>
          </div>
          <!-- 收藏按钮 -->
          <div class="favorite-btn" @click.stop="toggleStarred(item.businessId)">
            <i class="fa fa-heart favorite-icon favorite-red"></i>
          </div>
        </li>
      </ul>
    </div>

    <!-- 底部菜单部分 -->
    <Footer></Footer>
  </div>
</template>

<script>
import Footer from '../components/Footer.vue';

export default {
  name: 'StarredList',
  data() {
    return {
      favoriteArr: [],
      user: {},
      isLoading: false
    };
  },
  created() {
    this.user = this.$getSessionStorage('user');
    if (this.user.userId) {
      this.loadStarList();
    } else {
      this.$router.push('/login');
    }
  },
  components: {
    Footer
  },
  methods: {
    // 加载收藏列表
    loadStarList() {
      this.isLoading = true;
      this.$axios.post('UserController/listStarBusinessById', this.$qs.stringify({
        userId: this.user.userId
      })).then((response) => {
        this.favoriteArr = response.data;
        this.loadCartQuantity();
      }).catch((error) => {
        console.error('加载收藏列表失败', error);
        alert('加载收藏失败，请重试');
      }).finally(() => {
        this.isLoading = false;
      });
    },

    // 加载购物车数量
    loadCartQuantity() {
      this.$axios.post('CartController/listCart', this.$qs.stringify({
        userId: this.user.userId
      })).then((response) => {
        const cartArr = response.data;
        for (const business of this.favoriteArr) {
          business.quantity = 0;
          for (const cartItem of cartArr) {
            if (cartItem.businessId === business.businessId) {
              business.quantity += cartItem.quantity;
            }
          }
        }
      }).catch((error) => {
        console.error('加载购物车数量失败', error);
      });
    },

    // 切换收藏状态
    toggleStarred(businessId) {
      if (!this.user.userId) {
        this.$router.push('/login');
        return;
      }

      const businessIndex = this.favoriteArr.findIndex((item) => item.businessId === businessId);
      if (businessIndex === -1) return;

      this.$set(this.favoriteArr[businessIndex], 'isStarred', false);

      this.$axios.post('BusinessController/unstarBusinessById', this.$qs.stringify({
        businessId: businessId,
        userId: this.user.userId
      })).then((response) => {
        if (response.data === 1) {
          this.favoriteArr.splice(businessIndex, 1);
        } else {
          this.$set(this.favoriteArr[businessIndex], 'isStarred', true);
          alert('取消收藏失败');
        }
      }).catch((error) => {
        console.error('取消收藏接口调用失败', error);
        this.$set(this.favoriteArr[businessIndex], 'isStarred', true);
        alert('操作失败，请重试');
      });
    },

    // 跳转到商家详情
    toBusinessInfo(businessId) {
      this.$router.push({ path: '/businessInfo', query: { businessId: businessId } });
    }
  }
};
</script>

<style scoped>
/****************** 总容器 ******************/
.wrapper {
  position: relative;
  width: 100%;
  height: 100vh;
  overflow: hidden;
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

/****************** 内容区域 ******************/
.content-area {
  position: absolute;
  top: 12vw;
  bottom: 14vw;
  left: 0;
  right: 0;
  overflow-y: auto;
  background-color: #f8f8f8;
}

/****************** 空收藏背景提示 ******************/
.empty-background {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #fff;
  padding: 0 4vw;
  box-sizing: border-box;
}

.empty-content {
  text-align: center;
  max-width: 80%;
}

.empty-icon {
  font-size: 18vw;
  color: #ffcc80;
  margin-bottom: 6vw;
  display: block;
}

.empty-title {
  font-size: 4.2vw;
  color: #795548;
  font-weight: 600;
  margin-bottom: 2vw;
}

.empty-subtitle {
  font-size: 3.6vw;
  color: #a1887f;
  margin-top: 0;
}

/****************** 收藏列表部分 ******************/
.wrapper .business {
  width: 100%;
  margin: 0;
  padding: 0;
  list-style: none;
}

.wrapper .business li {
  width: 100%;
  box-sizing: border-box;
  padding: 3vw;
  border-bottom: solid 1px #DDD;
  user-select: none;
  cursor: pointer;
  display: flex;
  align-items: center;
  background-color: #fff;
  position: relative;
}

.wrapper .business li .business-img {
  position: relative;
  margin-right: 3vw;
  flex-shrink: 0;
}

.wrapper .business li .business-img img {
  width: 20vw;
  height: 20vw;
  border-radius: 4px;
  object-fit: cover;
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
  display: flex;
  flex-direction: column;
  justify-content: center;
  overflow: hidden;
  margin-right: 10vw;
}

.business-name {
  font-size: 3.8vw;
  color: #555;
  margin-bottom: 1vw;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.price-info {
  font-size: 3vw;
  color: #888;
  margin-top: 0.5vw;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 1;
  line-clamp: 1;
  overflow: hidden;
}

.explain-info {
  font-size: 3vw;
  color: #888;
  margin-top: 0.5vw;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 1;
  line-clamp: 1;
  overflow: hidden;
}

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
  color: #ff4d4f;
}
</style>