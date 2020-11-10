export default function AuthHeader() {
    const user = JSON.parse(localStorage.getItem('user'));
  
    if (user && user.token) {
      console.log("AuthHeader user", user.token);
      return { Authorization: user.token };
    } else {
      return {};
    }
}