import { useEffect } from 'react';
import { useHistory } from 'react-router-dom';
import ReactGA from 'react-ga';

function App() {
  const history = useHistory();

  useEffect(() => {
    // Initialize Google Analytics
    ReactGA.initialize('UA-XXXXXXXXX-X'); // Replace with your Tracking ID

    // Track initial page view
    ReactGA.pageview(window.location.pathname);

    // Track page view on route changes
    const unlisten = history.listen((location) => {
      ReactGA.pageview(location.pathname);
    });

    // Cleanup
    return () => {
      unlisten();
    };
  }, [history]);

  return (

  );
}

export default App;