import React, { useState, useEffect } from 'react';
import { Row, Col, Container } from 'react-bootstrap';
import Sidenav from '../components/Sidenav';
import PrizeCard from '../components/PrizeCard.js';
import amazon from '../images/amazon.png';
import visa from '../images/visa.png';
import chipotle from '../images/chipotle.png';
import { useAuth } from '../components/AuthContext';

const Prizes = () => {
    const { isLoggedIn } = useAuth();
    const [shouldRedirect, setShouldRedirect] = useState(false);

    useEffect(() => {
        const timer = setTimeout(() => {
            setShouldRedirect(!isLoggedIn);
        }, 100);
        return () => clearTimeout(timer);
    }, [isLoggedIn]);

    // If not logged in, redirect to login page
    if (shouldRedirect) {
        window.location.href = '/login';
        return null;
    }

  return (
    <div className="container-fluid">
        <div className="row">
            <div className="col-md-3">
                <Sidenav />
            </div>

          <div className="col-md-9">
            <main role="main" className="px-4">
              <div className="colorWhite">
                <h1> Prizes </h1>
                 <br />

                <Container fluid="sm">
                    <Row className="justify-content-center">
                              <Col>
                                  <PrizeCard
                                      id={1}
                                      title={"Amazon Gift Card"}
                                      image={amazon}
                                  />
                              </Col>
                              <Col>
                                  <PrizeCard
                                      id={2}
                                      title={"Pre-paid Visa"}
                                      image={visa}
                                  />
                              </Col>
                              <Col>
                                  <PrizeCard
                                      id={3}
                                      title={"Chipotle Meal"}
                                      image={chipotle}
                                  />
                              </Col>
                    </Row>
                </Container>
              </div>
            </main>
          </div>
        </div>
      </div>
  );
}

export default Prizes;
