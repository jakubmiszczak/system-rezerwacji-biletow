import { Link, useLocation } from 'react-router-dom';
import './Navigation.css';

const Navigation = () => {
  const location = useLocation();

  return (
    <nav className="navigation">
      <div className="nav-tabs">
        <Link 
          to="/home" 
          className={`nav-tab ${location.pathname === '/home' ? 'active' : ''}`}
        >
          Home
        </Link>
        <Link 
          to="/accounts" 
          className={`nav-tab ${location.pathname === '/accounts' ? 'active' : ''}`}
        >
          Accounts
        </Link>
      </div>
      <div className="user-icon">
        <div className="avatar">
          <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
            <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/>
            <circle cx="12" cy="7" r="4"/>
          </svg>
        </div>
      </div>
    </nav>
  );
};

export default Navigation; 