import { useState, useEffect } from 'react';
import { userService } from '../services/api';
import Account from '../components/Account';
import './Accounts.css';

const Accounts = () => {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [expandedAccounts, setExpandedAccounts] = useState(new Set());

  useEffect(() => {
    fetchUsers();
  }, []);

  const fetchUsers = async () => {
    try {
      const data = await userService.getBasicUsers();
      setUsers(data);
    } catch (err) {
      setError('Failed to load accounts');
    } finally {
      setLoading(false);
    }
  };

  const toggleAccount = (userId) => {
    setExpandedAccounts(prev => {
      const newSet = new Set(prev);
      if (newSet.has(userId)) {
        newSet.delete(userId);
      } else {
        newSet.add(userId);
      }
      return newSet;
    });
  };

  if (loading) {
    return (
      <div className="accounts-page">
        <div className="loading">Loading accounts...</div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="accounts-page">
        <div className="error">{error}</div>
      </div>
    );
  }

  return (
    <div className="accounts-page">
      <div className="accounts-container">
        {users.length === 0 ? (
          <div className="no-accounts">No accounts found.</div>
        ) : (
          users.map(user => (
            <Account
              key={user.id}
              user={user}
              isExpanded={expandedAccounts.has(user.id)}
              onToggle={() => toggleAccount(user.id)}
            />
          ))
        )}
      </div>
    </div>
  );
};

export default Accounts; 