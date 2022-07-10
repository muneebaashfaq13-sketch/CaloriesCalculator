import './App.css';
import RequireAuth from './components/RequireAuth';
import { Routes, Route } from 'react-router-dom';
import Authenticate from './components/Authenticate';
import Layout from './components/Layout';
import Admin from './components/admin/Admin';
import User from './components/user/User';

function App() {

  return (
    <Routes>
      <Route path="/" element={<Layout />}>

        <Route path="login" element={<Authenticate />} />

        <Route element={<RequireAuth allowedRoles={['Admin']} />}>
          <Route path="admin" element={<Admin />} />
        </Route>

        <Route element={<RequireAuth allowedRoles={['User']} />}>
          <Route path="user" element={<User />} />
        </Route>
      </Route>

      <Route path="*" element={<Authenticate />} />
    </Routes>
  );
}

export default App;
