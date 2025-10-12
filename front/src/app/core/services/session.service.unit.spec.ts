// import { TestBed } from '@angular/core/testing';
// import { expect } from '@jest/globals';
//
// import { SessionService } from './session.service';
//
// describe('SessionService', () => {
//   let service: SessionService;
//
//   beforeEach(() => {
//     TestBed.configureTestingModule({});
//     service = TestBed.inject(SessionService);
//   });
//
//   it('should be created', () => {
//     expect(service).toBeTruthy();
//   });
//
//   describe('initial state', () => {
//     it('should have isLogged as false initially', () => {
//       expect(service.isLogged).toBe(false);
//     });
//
//     it('should have sessionInformation as undefined initially', () => {
//       expect(service.sessionInformation).toBeUndefined();
//     });
//   });
//
//   describe('$isLogged', () => {
//     it('should return an observable', () => {
//       const observable = service.$isLogged();
//       expect(observable).toBeDefined();
//       expect(typeof observable.subscribe).toBe('function');
//     });
//
//     it('should emit initial value when subscribed', (done) => {
//       service.$isLogged().subscribe(isLogged => {
//         expect(isLogged).toBe(false);
//         done();
//       });
//     });
//   });
//
//   describe('logIn', () => {
//     it('should set sessionInformation and isLogged to true', () => {
//       const mockUser: SessionInformation = {
//         token: 'mock-token',
//         type: 'Bearer',
//         id: 1,
//         username: 'testuser',
//         firstName: 'Test',
//         lastName: 'User',
//         admin: false
//       };
//
//       service.logIn(mockUser);
//
//       expect(service.sessionInformation).toEqual(mockUser);
//       expect(service.isLogged).toBe(true);
//     });
//
//     it('should emit true through the observable when logging in', (done) => {
//       const mockUser: SessionInformation = {
//         token: 'mock-token',
//         type: 'Bearer',
//         id: 1,
//         username: 'testuser',
//         firstName: 'Test',
//         lastName: 'User',
//         admin: false
//       };
//
//       service.$isLogged().subscribe(isLogged => {
//         if (isLogged) {
//           expect(isLogged).toBe(true);
//           done();
//         }
//       });
//
//       service.logIn(mockUser);
//     });
//   });
//
//   describe('logOut', () => {
//     beforeEach(() => {
//       const mockUser: SessionInformation = {
//         token: 'mock-token',
//         type: 'Bearer',
//         id: 1,
//         username: 'testuser',
//         firstName: 'Test',
//         lastName: 'User',
//         admin: false
//       };
//       service.logIn(mockUser);
//     });
//
//     it('should set sessionInformation to undefined and isLogged to false', () => {
//       service.logOut();
//
//       expect(service.sessionInformation).toBeUndefined();
//       expect(service.isLogged).toBe(false);
//     });
//
//     it('should emit false through the observable when logging out', (done) => {
//       service.$isLogged().subscribe(isLogged => {
//         if (!isLogged) {
//           expect(isLogged).toBe(false);
//           done();
//         }
//       });
//
//       service.logOut();
//     });
//   });
//
//   describe('observable behavior', () => {
//     it('should emit values in correct sequence', () => {
//       const emittedValues: boolean[] = [];
//       const mockUser: SessionInformation = {
//         token: 'mock-token',
//         type: 'Bearer',
//         id: 1,
//         username: 'testuser',
//         firstName: 'Test',
//         lastName: 'User',
//         admin: false
//       };
//
//       service.$isLogged().subscribe(isLogged => {
//         emittedValues.push(isLogged);
//       });
//
//       expect(emittedValues).toEqual([false]); // valeur initiale
//
//       service.logIn(mockUser);
//       expect(emittedValues).toEqual([false, true]);
//
//       service.logOut();
//       expect(emittedValues).toEqual([false, true, false]);
//     });
//   });
// });
