<template>
  <div class="profile-container">
    <!-- 用户信息卡片 -->
    <div class="user-card">
      <div class="avatar-container">
        <div class="avatar-wrapper">
          <img :src="avatarSrc" alt="用户头像" class="avatar">
        </div>
      </div>
      <div class="user-info">
        <h2>{{ user.userName || 'momo' }}</h2>
        <p class="phone">用户ID：{{ user.userId }}</p>
        <p class="phone">性别：{{ user.userSex === 1 ? '男' : '女' }}</p>
      </div>
    </div>

    <!-- 退出登录按钮 -->
    <div class="logout-section">
      <button class="logout-btn" @click="logout">
        退出登录
      </button>
    </div>

    <!-- 底部导航栏 -->
    <Footer></Footer>
  </div>
</template>

<script>
import Footer from "../components/Footer.vue";
import { loadUserImage } from "@/utils/imageCache.js";

export default {
  name: 'Profile',
  data() {
    return {
      user: {
        userId: '',
        userName: '',
        userSex: '',
        userImg: '',
        password: ''
      },
      defaultAvatar: require('../assets/default_avatar.png'),
      cachedAvatar: ''
    };
  },
  created() {
    this.loadUserData();
  },
  computed: {
    avatarSrc() {
      return this.cachedAvatar || this.user.userImg || this.defaultAvatar;
    }
  },
  components: {
    Footer
  },
  methods: {
    async loadUserData() {
      const userData = sessionStorage.getItem('user');
      if (userData) {
        try {
          const parsedData = JSON.parse(userData);
          this.user = {
            userId: parsedData.userId,
            userName: parsedData.userName || 'momo',
            userSex: parsedData.userSex,
            userImg: parsedData.userImg,
            password: parsedData.password
          };

          if (parsedData.userId) {
            this.cachedAvatar = await loadUserImage(parsedData.userId);
          }
        } catch (e) {
          console.error('解析用户数据错误:', e);
          this.$router.replace('/login');
        }
      } else {
        this.$router.replace('/login');
      }
    },

    logout() {
      sessionStorage.removeItem('user');
      this.showToast('已退出登录');
      setTimeout(() => {
        this.$router.replace('/login');
      }, 800);
    },

    showToast(message) {
      const existingToast = document.querySelector('.toast');
      if (existingToast) existingToast.remove();

      const toast = document.createElement('div');
      toast.className = 'toast';
      toast.textContent = message;

      document.body.appendChild(toast);

      setTimeout(() => toast.classList.add('show'), 10);

      setTimeout(() => {
        toast.classList.remove('show');
        setTimeout(() => toast.remove(), 300);
      }, 3000);
    }
  }
}
</script>

<style scoped>
.profile-container {
  display: flex;
  flex-direction: column;
  min-height: calc(100vh - 14vw);
  background-color: #f8f8f8;
  padding: 20px 20px 14vw;
}

.user-card {
  background: white;
  border-radius: 16px;
  padding: 30px 20px;
  margin-top: 30px;
  display: flex;
  align-items: center;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
  transition: all 0.3s ease;
}

.user-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 6px 16px rgba(0, 0, 0, 0.12);
}

.avatar-container {
  width: 80px;
  height: 80px;
  margin-right: 20px;
  border-radius: 50%;
  overflow: hidden;
  background: linear-gradient(45deg, #ff7e5f, #feb47b);
  display: flex;
  justify-content: center;
  align-items: center;
}

.avatar-wrapper {
  width: 70px;
  height: 70px;
  border-radius: 50%;
  overflow: hidden;
  background-color: white;
  display: flex;
  justify-content: center;
  align-items: center;
}

.avatar {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.user-info h2 {
  font-size: 24px;
  color: #333;
  margin-bottom: 8px;
  font-weight: 600;
}

.user-info .phone {
  font-size: 16px;
  color: #666;
  letter-spacing: 0.5px;
  margin: 4px 0;
}

.logout-section {
  margin-top: 40px;
  display: flex;
  justify-content: center;
}

.logout-btn {
  padding: 15px 45px;
  background: linear-gradient(to right, #ff7e5f, #feb47b);
  color: white;
  font-size: 18px;
  font-weight: 600;
  border: none;
  border-radius: 30px;
  cursor: pointer;
  transition: all 0.3s ease;
  box-shadow: 0 4px 15px rgba(254, 180, 123, 0.4);
}

.logout-btn:hover {
  transform: scale(1.05);
  box-shadow: 0 6px 20px rgba(254, 180, 123, 0.6);
}

.logout-btn:active {
  transform: scale(0.98);
}

.toast {
  position: fixed;
  bottom: 40px;
  left: 50%;
  transform: translateX(-50%) translateY(100px);
  background: rgba(0, 0, 0, 0.7);
  color: white;
  padding: 12px 24px;
  border-radius: 30px;
  font-size: 16px;
  font-weight: 500;
  opacity: 0;
  transition: all 0.3s ease;
  white-space: nowrap;
  z-index: 1000;
}

.toast.show {
  transform: translateX(-50%) translateY(0);
  opacity: 1;
}

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