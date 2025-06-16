<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>我的订单</p>
    </header>
    <!-- 订单列表部分 -->
    <h3>未支付订单信息：</h3>
    <ul class="order">
      <li v-for="item in orderArr" v-if="item.orderState === 0" :key="item.orderId">
        <div class="order-info">
          <p>
            {{ item.business.businessName }}
            <i class="fa fa-caret-down" @click="detailetShow(item)"></i>
          </p>
          <div class="order-info-right">
            <p>&#165;{{ item.orderTotal }}</p>
            <button class="go-pay-btn" @click="goToPayment(item.orderId)">去支付</button>
          </div>
        </div>
        <ul class="order-detailet" v-show="item.isShowDetailet">
          <li v-for="odItem in item.list" :key="odItem.id">
            <p>{{ odItem.food.foodName }} x {{ odItem.quantity }}</p>
            <p>&#165;{{ odItem.food.foodPrice * odItem.quantity }}</p>
          </li>
          <li>
            <p>配送费</p>
            <p>&#165;{{ item.business.deliveryPrice }}</p>
          </li>
        </ul>
      </li>
    </ul>

    <h3>已支付订单信息：</h3>
    <ul class="order">
      <li v-for="item in orderArr" v-if="item.orderState === 1" :key="item.orderId">
        <div class="order-info">
          <p>
            {{ item.business.businessName }}
            <i class="fa fa-caret-down" @click="detailetShow(item)"></i>
          </p>
          <div class="order-info-right">
            <p>&#165;{{ item.orderTotal }}</p>
            <!-- 已支付图标 -->
            <span class="order-status-tag paid">已支付</span>
          </div>
        </div>
        <ul class="order-detailet" v-show="item.isShowDetailet">
          <li v-for="odItem in item.list" :key="odItem.id">
            <p>{{ odItem.food.foodName }} x {{ odItem.quantity }}</p>
            <p>&#165;{{ odItem.food.foodPrice * odItem.quantity }}</p>
          </li>
          <li>
            <p>配送费</p>
            <p>&#165;{{ item.business.deliveryPrice }}</p>
          </li>
        </ul>
      </li>
    </ul>

    <!-- 底部菜单部分 -->
    <Footer></Footer>
  </div>
</template>

<script>
import Footer from '../components/Footer.vue';

export default {
  name: 'OrderList',
  data() {
    return {
      orderArr: [],
      user: {},
      isLoading: false
    }
  },
  created() {
    this.loadOrderList();
  },
  methods: {
    loadOrderList() {
      this.isLoading = true;
      this.user = this.$getSessionStorage('user');
      this.$axios.post('OrdersController/listOrdersByUserId', this.$qs.stringify({
        userId: this.user.userId
      })).then(response => {
        const result = response.data;
        for (const order of result) {
          order.isShowDetailet = false;
        }
        this.orderArr = result;
      }).catch(error => {
        console.error('获取订单列表失败', error);
      }).finally(() => {
        this.isLoading = false;
      });
    },

    detailetShow(orders) {
      orders.isShowDetailet = !orders.isShowDetailet;
    },

    goToPayment(orderId) {
      if (this.isLoading) return;

      this.isLoading = true;
      this.$router.push({
        name: 'Payment',
        query: {orderId: orderId}
      }).finally(() => {
        this.isLoading = false;
      });
    }
  },
  components: {
    Footer
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

/****************** 历史订单列表部分 ******************/
.wrapper h3 {
  margin-top: 12vw;
  box-sizing: border-box;
  padding: 4vw;
  font-size: 4vw;
  font-weight: 300;
  color: #999;
}

.wrapper .order {
  width: 100%;
}

.wrapper .order li {
  width: 100%;
  border-bottom: 1px solid #f5f5f5;
}

.wrapper .order li .order-info {
  box-sizing: border-box;
  padding: 3vw 4vw;
  font-size: 4vw;
  color: #666;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #fff;
}

.wrapper .order li .order-info .order-info-right {
  display: flex;
  align-items: center;
}

.wrapper .order li .order-info .order-info-right p {
  margin-right: 2vw;
  color: #f90;
  font-weight: bold;
}

.wrapper .order li .order-info .order-info-right .go-pay-btn {
  background-color: #ff6b6b;
  color: #fff;
  border: none;
  border-radius: 6px;
  padding: 1vw 3vw;
  font-size: 3vw;
  cursor: pointer;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 8vw;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.wrapper .order li .order-info .order-info-right .go-pay-btn:active {
  background-color: #ff4d4f;
  transform: scale(0.96);
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
  transition: all 0.2s;
}

.wrapper .order li .order-info .order-info-right .order-status-tag {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  padding: 0.5vw 2vw;
  font-size: 2.8vw;
  border-radius: 4px;
  color: #fff;
  margin-left: 2vw;
}

/* 已支付状态 */
.wrapper .order li .order-info .order-info-right .order-status-tag.paid {
  background-color: #38CA73;
}

/* 未支付状态：橙色背景 */
.wrapper .order li .order-info .order-info-right .go-pay-btn {
  background-color: #ff6b6b;
}

.wrapper .order li .order-detailet {
  width: 100%;
  background-color: #f9f9f9;
}

.wrapper .order li .order-detailet li {
  width: 100%;
  box-sizing: border-box;
  padding: 2vw 4vw;
  color: #666;
  font-size: 3vw;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.wrapper .order li .order-info .order-info-right .go-pay-btn.loading {
  background-color: #ccc;
  cursor: not-allowed;
}

.wrapper .order li .order-info .order-info-right .go-pay-btn.loading::after {
  content: ' ';
  display: inline-block;
  width: 4vw;
  height: 4vw;
  margin-left: 2vw;
  border: 2px solid #fff;
  border-radius: 50%;
  border-top-color: transparent;
  animation: spin 1s linear infinite;
  vertical-align: middle;
}

@keyframes spin {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}
</style>